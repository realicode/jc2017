package com.realaicy.prod.jc.lib.core.mapper;


import com.realaicy.prod.jc.lib.core.utils.Exceptions;
import com.realaicy.prod.jc.lib.core.utils.Reflections;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 使用Jaxb2.0实现XML<->Java Object的Mapper.
 * <p/>
 * 在创建时需要设定所有需要序列化的Root对象的Class.
 * 特别支持Root对象是Collection的情形.
 *
 * @author realaicy
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class JaxbMapper {


    /**
     * Do not instantiate JaxbMapper.
     */
    private JaxbMapper() {

    }

    /**
     * XXX
     */
    private static ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<>();

    /**
     * Java Object->Xml without encoding.
     *
     * @param root the root
     * @return the string
     */
    public static String toXml(final Object root) {
        Class clazz = Reflections.getUserClass(root);
        return toXml(root, clazz, null);
    }

    /**
     * Java Object->Xml with encoding.
     *
     * @param root     the root
     * @param encoding the encoding
     * @return the string
     */
    public static String toXml(final Object root, final String encoding) {
        Class clazz = Reflections.getUserClass(root);
        return toXml(root, clazz, encoding);
    }

    /**
     * Java Object->Xml with encoding.
     *
     * @param root     the root
     * @param clazz    the clazz
     * @param encoding the encoding
     * @return the string
     */
    public static String toXml(final Object root, final Class clazz, final String encoding) {
        try {
            StringWriter writer = new StringWriter();
            createMarshaller(clazz, encoding).marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * Java Collection->Xml without encoding, 特别支持Root Element是Collection的情形.
     *
     * @param root     the root
     * @param rootName the root name
     * @param clazz    the clazz
     * @return the string
     */
    public static String toXml(final Collection<?> root, final String rootName, final Class clazz) {
        return toXml(root, rootName, clazz, null);
    }

    /**
     * Java Collection->Xml with encoding, 特别支持Root Element是Collection的情形.
     *
     * @param root     the root
     * @param rootName the root name
     * @param clazz    the clazz
     * @param encoding the encoding
     * @return the string
     */
    public static String toXml(final Collection<?> root, final String rootName, final Class clazz,
                               final String encoding) {
        try {
            CollectionWrapper wrapper = new CollectionWrapper();
            wrapper.collection = root;

            JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<>(new QName(rootName),
                    CollectionWrapper.class, wrapper);

            StringWriter writer = new StringWriter();
            createMarshaller(clazz, encoding).marshal(wrapperElement, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * Xml->Java Object.
     *
     * @param <T>   the type parameter
     * @param xml   the xml
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T fromXml(final String xml, final Class<T> clazz) {
        try {
            StringReader reader = new StringReader(xml);
            return (T) createUnmarshaller(clazz).unmarshal(reader);
        } catch (JAXBException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 创建Marshaller并设定encoding(可为null).
     * 线程不安全，需要每次创建或pooling。
     *
     * @param clazz    the clazz
     * @param encoding the encoding
     * @return the marshaller
     */
    public static Marshaller createMarshaller(final Class clazz, final String encoding) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            if (StringUtils.isNotBlank(encoding)) {
                marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            }

            return marshaller;
        } catch (JAXBException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 创建UnMarshaller.
     * 线程不安全，需要每次创建或pooling。
     *
     * @param clazz the clazz
     * @return the unmarshaller
     */
    public static Unmarshaller createUnmarshaller(final Class clazz) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * Gets jaxb context.
     *
     * @param clazz the clazz
     * @return the jaxb context
     */
    protected static JAXBContext getJaxbContext(final Class clazz) {
        Validate.notNull(clazz, "'clazz' must not be null");
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz, CollectionWrapper.class);
                jaxbContexts.putIfAbsent(clazz, jaxbContext);
            } catch (JAXBException ex) {
                throw new RuntimeException("Could not instantiate JAXBContext for class [" + clazz + "]: "
                        + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }

    /**
     * 封装Root Element 是 Collection的情况.
     */
    public static class CollectionWrapper {

        /**
         * The Collection.
         */
        @XmlAnyElement
        private Collection<?> collection;
    }
}
